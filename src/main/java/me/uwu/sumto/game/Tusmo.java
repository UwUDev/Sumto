package me.uwu.sumto.game;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.uwu.sumto.Solver;
import me.uwu.sumto.dico.DicoLang;
import okhttp3.*;

public class Tusmo {
    private final String shortId, playerId;
    private final Solver solver;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public Tusmo(String shortId, String playerId) throws Exception {
        this.shortId = shortId;
        this.playerId = playerId;

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"operationName\": \"JoinMotus\",\n  \"variables\": {\n    \"shortId\": \"" + shortId + "\",\n    \"playerId\": \"" + playerId + "\",\n    \"playerName\": null,\n    \"accessToken\": \"\"\n  },\n  \"query\": \"mutation JoinMotus($shortId: ID!, $playerId: ID!, $playerName: String, $accessToken: String) {\\n  joinMotus(\\n    shortId: $shortId\\n    playerId: $playerId\\n    playerName: $playerName\\n    accessToken: $accessToken\\n  ) {\\n    shortId\\n    type\\n    state\\n    lang\\n    currentRound\\n    isStarted\\n    isEnded\\n    playersNumber\\n    rounds {\\n      _id\\n      firstLetter\\n      length\\n      __typename\\n    }\\n    me {\\n      _id\\n      name\\n      hasWon\\n      currentRound\\n      state\\n      rounds {\\n        score\\n        hasFoundWord\\n        tries {\\n          word\\n          validation\\n          wordExists\\n          hasFoundWord\\n          mask\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    __typename\\n  }\\n}\"\n}");
        Request request = new Request.Builder()
                .url("https://www.tusmo.xyz/graphql?opname=JoinMotus")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        JsonObject json = gson.fromJson(client.newCall(request).execute().body().string(), JsonObject.class);

        DicoLang dicoLang = DicoLang.getDicoLangByIdentifier(json.get("data").getAsJsonObject().get("joinMotus").getAsJsonObject().get("lang").getAsString());
        System.out.println("Lang: " + dicoLang.name);

        JsonArray rounds = json.get("data").getAsJsonObject().get("joinMotus").getAsJsonObject().get("rounds").getAsJsonArray();

        char firstChar = rounds.get(rounds.size() - 1).getAsJsonObject().get("firstLetter").getAsString().charAt(0);
        int len = rounds.get(rounds.size() - 1).getAsJsonObject().get("length").getAsInt();

        System.out.println("Starting with: " + firstChar + " " + len + " words.");

        solver = new Solver(dicoLang, len, firstChar);
    }

    public void play() throws Exception{
        boolean found = false;
        while (!found) {
            String move = solver.getRandomMove();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n  \"operationName\": \"TryWord\",\n  \"variables\": {\n    \"word\": \"" + move + "\",\n    \"shortId\": \"" + shortId + "\",\n    \"playerId\": \"" + playerId + "\",\n    \"accessToken\": \"\",\n    \"lang\": \"fr\"\n  },\n  \"query\": \"mutation TryWord($shortId: ID!, $word: String!, $playerId: ID!, $lang: String!, $accessToken: String) {\\n  tryWord(\\n    shortId: $shortId\\n    word: $word\\n    playerId: $playerId\\n    lang: $lang\\n    accessToken: $accessToken\\n  ) {\\n    word\\n    validation\\n    wordExists\\n    hasFoundWord\\n    mask\\n    score\\n    __typename\\n  }\\n}\"\n}");
            Request request = new Request.Builder()
                    .url("https://www.tusmo.xyz/graphql?opname=TryWord")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            JsonObject json = gson.fromJson(client.newCall(request).execute().body().string(), JsonObject.class);

            found = json.get("data").getAsJsonObject().get("tryWord").getAsJsonObject().get("hasFoundWord").getAsBoolean();
            if (found)
                break;

            StringBuilder sb = new StringBuilder();
            for (JsonElement jsonElement : json.get("data").getAsJsonObject().get("tryWord").getAsJsonObject().get("validation").getAsJsonArray()) {
                sb.append(jsonElement.getAsString());
            }
            solver.filter(move, sb.toString());
            solver.dico.dico.forEach(System.out::println);
            System.out.println(solver.dico.dico.size());
        }
    }
}
