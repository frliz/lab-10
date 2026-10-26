
public class CampusAI {

    public static void main(String[] args) {

        String[] models = {
            "ChatGPT", "Gemini", "Claude",
            "Copilot", "Perplexity"
        };

        String[] tasks = {
            "Writing", "Research", "Coding",
            "Math", "Creativity"
        };

        int[][] scores = {
            {92, 88, 95, 90, 86},
            {89, 94, 87, 85, 91},
            {95, 90, 89, 92, 84},
            {85, 82, 96, 88, 90},
            {91, 97, 80, 86, 93}
        };

        System.out.println("=== CAMPUSAI PERFORMANCE REPORT ===");

        displayScores(models, tasks, scores);

        System.out.println("\nMODEL AVERAGES");

        for (int i = 0; i < models.length; i++) {
            double average = averageModel(scores, i);
            System.out.printf("%s: %.2f%n", models[i], average);
        }

        System.out.println("\nCATEGORY AVERAGES");

        for (int i = 0; i < tasks.length; i++) {
            double average = averageTask(scores, i);
            System.out.printf("%s: %.2f%n", tasks[i], average);
        }

        System.out.println("\nSEARCH RESULTS");

        int found = searchModel(models, "Claude");

        if (found != -1) {
            System.out.println("Claude found at index " + found);
        } else {
            System.out.println("Claude not found");
        }

        int notFound = searchModel(models, "UnknownAI");

        if (notFound != -1) {
            System.out.println("UnknownAI found at index " + notFound);
        } else {
            System.out.println("UnknownAI not found");
        }

        System.out.println("\nBEST MODEL BY CATEGORY");

        for (int i = 0; i < tasks.length; i++) {
            int best = findBestModel(scores, i);

            System.out.println(tasks[i] + ": " +
                models[best] + " (" + scores[best][i] + ")");
        }

        double[] averages = new double[models.length];
        String[] leaderboardModels = new String[models.length];

        for (int i = 0; i < models.length; i++) {
            averages[i] = averageModel(scores, i);
            leaderboardModels[i] = models[i];
        }

        sortLeaderboard(leaderboardModels, averages);

        System.out.println("\nFINAL LEADERBOARD");

        for (int i = 0; i < leaderboardModels.length; i++) {
            System.out.printf("%d. %s %.2f%n",
                i + 1, leaderboardModels[i], averages[i]);
        }
    }

    public static void displayScores(
        String[] models,
        String[] tasks,
        int[][] scores) {

        System.out.println("\nPERFORMANCE MATRIX");

        System.out.printf("%-15s", "AI Model");

        for (int i = 0; i < tasks.length; i++) {
            System.out.printf("%-12s", tasks[i]);
        }

        System.out.println();

        for (int i = 0; i < scores.length; i++) {
            System.out.printf("%-15s", models[i]);

            for (int j = 0; j < scores[i].length; j++) {
                System.out.printf("%-12d", scores[i][j]);
            }

            System.out.println();
        }
    }

    public static double averageModel(int[][] scores, int row) {

        int total = 0;

        for (int i = 0; i < scores[row].length; i++) {
            total += scores[row][i];
        }

        return (double) total / scores[row].length;
    }

    public static double averageTask(int[][] scores, int column) {

        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i][column];
        }

        return (double) total / scores.length;
    }

    public static int searchModel(String[] models, String target) {

        for (int i = 0; i < models.length; i++) {
            if (models[i].equalsIgnoreCase(target)) {
                return i;
            }
        }

        return -1;
    }

    public static int findBestModel(int[][] scores, int taskIndex) {

        int bestIndex = 0;

        for (int i = 1; i < scores.length; i++) {
            if (scores[i][taskIndex] > scores[bestIndex][taskIndex]) {
                bestIndex = i;
            }
        }

        return bestIndex;
    }

    public static void sortLeaderboard(
        String[] models, double[] averages) {

        for (int i = 0; i < averages.length - 1; i++) {

            int highestIndex = i;

            for (int j = i + 1; j < averages.length; j++) {
                if (averages[j] > averages[highestIndex]) {
                    highestIndex = j;
                }
            }

            double tempAverage = averages[i];
            averages[i] = averages[highestIndex];
            averages[highestIndex] = tempAverage;

            String tempModel = models[i];
            models[i] = models[highestIndex];
            models[highestIndex] = tempModel;
        }
    }
}
