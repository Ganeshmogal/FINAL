import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class demo {
    static int[][] distanceMatrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        int numCities = scanner.nextInt();

        distanceMatrix = new int[numCities][numCities];

        System.out.println();
        for (int i = 0; i < numCities; i++) {
            int j = i;
            distanceMatrix[i][j] = 0;
        }
        for (int i = 0; i < numCities; i++) {
            for (int j = i+1; j < numCities; j++) {
                    System.out.print("Enter the distance between "+(i)+" and "+(j)+" :");
                    distanceMatrix[i][j] = scanner.nextInt();
                    distanceMatrix[j][i] = distanceMatrix[i][j];
            }
        }
        System.out.println("\n---------------Display---------------");
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                   System.out.print(distanceMatrix[i][j]+"\t");
            }
            System.out.println();
        }

        while (true) {
            System.out.println("\nChoose an algorithm:");
            System.out.println("1. Exact TSP (Brute-Force)");
            System.out.println("2. Approximation TSP (Nearest Neighbor)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    runExactTSP();
                    break;
                case 2:
                    runNearestNeighborTSP();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    public static void runExactTSP() {
        List<Integer> cities = new ArrayList<>();
        for (int i = 0; i < distanceMatrix.length; i++) {
            cities.add(i);
        }

        List<Integer> shortestRoute = null;
        int shortestDistance = Integer.MAX_VALUE;

        Permutations perm = new Permutations();
        for (List<Integer> permutedRoute : perm.permute(cities)) {
            int distance = calculateTotalDistance(permutedRoute);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                shortestRoute = new ArrayList<>(permutedRoute);
            }
        }

        System.out.println("Exact TSP Solution (Brute-Force):");
        System.out.println("Shortest Route: " + shortestRoute);
        System.out.println("Shortest Distance: " + shortestDistance);
    }

    public static void runNearestNeighborTSP() {
        int numCities = distanceMatrix.length;
        List<Integer> unvisitedCities = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            unvisitedCities.add(i);
        }

        int startCity = 0;
        int currentCity = startCity;
        List<Integer> route = new ArrayList<>();
        route.add(currentCity);
        unvisitedCities.remove((Integer) currentCity);

        while (!unvisitedCities.isEmpty()) {
            int nearestCity = -1;
            int minDistance = Integer.MAX_VALUE;
            for (int city : unvisitedCities) {
                int distance = distanceMatrix[currentCity][city];
                if (distance < minDistance) {
                    nearestCity = city;
                    minDistance = distance;
                }
            }

            if (nearestCity != -1) {
                currentCity = nearestCity;
                route.add(currentCity);
                unvisitedCities.remove((Integer) currentCity);
            }
        }

        route.add(startCity); // Complete the circuit
        int totalDistance = calculateTotalDistance(route);

        System.out.println("Approximation TSP Solution (Nearest Neighbor):");
        System.out.println("Route: " + route);
        System.out.println("Total Distance: " + totalDistance);
    }

    public static int calculateTotalDistance(List<Integer> route) {
        int totalDistance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += distanceMatrix[route.get(i)][route.get(i + 1)];
        }
        totalDistance += distanceMatrix[route.get(route.size() - 1)][route.get(0)]; // Return to the starting city
        return totalDistance;
    }
}

class Permutations {
    public List<List<Integer>> permute(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, List<Integer> nums) {
        if (tempList.size() == nums.size()) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.size(); i++) {
                if (tempList.contains(nums.get(i))) continue;
                tempList.add(nums.get(i));
                backtrack(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
