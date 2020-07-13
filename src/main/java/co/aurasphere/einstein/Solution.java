package co.aurasphere.einstein;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Solution {

	// List of all rules from the problems.
	private static Predicate<Combination> englishmanInRedHouse = xand(s -> s.nationality == Nationality.ENGLISHMAN,
			s -> s.color == Color.RED);
	private static Predicate<Combination> spaniardOwnsDog = xand(s -> s.nationality == Nationality.SPANIARD,
			s -> s.pet == Pet.DOG);
	private static Predicate<Combination> coffeeDrunkInGreenHouse = xand(s -> s.drink == Drink.COFFEE,
			s -> s.color == Color.GREEN);
	private static Predicate<Combination> ukrainianDrinksTea = xand(s -> s.nationality == Nationality.UKRAINIAN,
			s -> s.drink == Drink.TEA);
	private static Predicate<List<Combination>> greenHouseRightToIvory = isRightTo(s -> s.color == Color.GREEN,
			s -> s.color == Color.IVORY);
	private static Predicate<Combination> oldGoldSmokerOwnsSnail = xand(s -> s.smoke == Smoke.OLD_GOLD,
			s -> s.pet == Pet.SNAILS);
	private static Predicate<Combination> koolsSmokedInYellowHouse = xand(s -> s.smoke == Smoke.KOOLS,
			s -> s.color == Color.YELLOW);
	private static Predicate<Combination> milkDrunkInMiddleHouse = xand(s -> s.drink == Drink.MILK,
			s -> s.house == House.THREE);
	private static Predicate<Combination> norwegianLivesInFirstHouse = xand(s -> s.nationality == Nationality.NORWEGIAN,
			s -> s.house == House.ONE);
	private static Predicate<List<Combination>> chesterfieldsSmokerNextToFox = isNextTo(
			s -> s.smoke == Smoke.CHESTERFIELD, s -> s.pet == Pet.FOX);
	private static Predicate<List<Combination>> koolsSmokerNextToHorse = isNextTo(s -> s.smoke == Smoke.KOOLS,
			s -> s.pet == Pet.HORSE);
	private static Predicate<Combination> luckyStrikeSmokerDrinksOrangeJuice = xand(s -> s.smoke == Smoke.LUCKY_STRIKE,
			s -> s.drink == Drink.ORANGE_JUICE);
	private static Predicate<Combination> japaneseSmokesParliament = xand(s -> s.nationality == Nationality.JAPANESE,
			s -> s.smoke == Smoke.PARLIAMENT);
	private static Predicate<List<Combination>> norwegianLivesNextToBlueHouse = isNextTo(
			s -> s.nationality == Nationality.NORWEGIAN, s -> s.color == Color.BLUE);

	// Returns true if the house that matches the first predicate is left or right
	// to the house that matches the second predicate.
	private static Predicate<List<Combination>> isNextTo(Predicate<Combination> a, Predicate<Combination> b) {
		return l -> {
			int houseA = l.parallelStream().filter(a).findFirst().get().house.ordinal();
			int houseB = l.parallelStream().filter(b).findFirst().get().house.ordinal();
			return houseA == houseB + 1 || houseA == houseB - 1;
		};
	}

	// Returns true if the house that matches the first predicate is right
	// to the house that matches the second predicate.
	private static Predicate<List<Combination>> isRightTo(Predicate<Combination> a, Predicate<Combination> b) {
		return l -> {
			int houseA = l.parallelStream().filter(a).findFirst().get().house.ordinal();
			int houseB = l.parallelStream().filter(b).findFirst().get().house.ordinal();
			return houseA == houseB + 1;
		};
	}

	// Returns true if both predicates are true or both predicates are false (XAND).
	private static Predicate<Combination> xand(Predicate<Combination> a, Predicate<Combination> b) {
		return a.and(b).or(b.negate().and(a.negate()));
	}

	public static void main(String[] args) {

		// Generates all the possible houses.
		System.out.println("Generating all possible houses");
		List<Combination> possibleHouses = new ArrayList<>();
		for (House house : House.values()) {
			for (Color color : Color.values()) {
				for (Nationality nationality : Nationality.values()) {
					for (Drink drink : Drink.values()) {
						for (Smoke smoke : Smoke.values()) {
							for (Pet pet : Pet.values()) {
								possibleHouses.add(new Combination(house, color, nationality, drink, smoke, pet));
							}
						}
					}
				}
			}
		}

		// Removes all the houses that violates a rule.
		System.out.println("Checking single elements rules");
		possibleHouses = possibleHouses.parallelStream().filter(englishmanInRedHouse).filter(spaniardOwnsDog)
				.filter(coffeeDrunkInGreenHouse).filter(ukrainianDrinksTea).filter(oldGoldSmokerOwnsSnail)
				.filter(koolsSmokedInYellowHouse).filter(milkDrunkInMiddleHouse).filter(norwegianLivesInFirstHouse)
				.filter(luckyStrikeSmokerDrinksOrangeJuice).filter(japaneseSmokesParliament)
				.collect(Collectors.toList());

		// From all the possible houses, we generate all the possible dispositions of 5
		// elements.
		System.out.println("Generating all possible solutions");
		List<List<Combination>> solutions = new ArrayList<>();
		for (int i = 0; i < possibleHouses.size(); i++) {
			Combination houseOne = possibleHouses.get(i);
			for (int j = 0; j < possibleHouses.size(); j++) {
				if (j == i)
					continue;
				Combination houseTwo = possibleHouses.get(j);
				if (houseTwo.isDuplicate(houseOne))
					continue;
				for (int k = 0; k < possibleHouses.size(); k++) {
					if (k == j || k == i)
						continue;

					Combination houseThree = possibleHouses.get(k);
					if (houseThree.isDuplicate(houseTwo) || houseThree.isDuplicate(houseOne))
						continue;
					for (int l = 0; l < possibleHouses.size(); l++) {
						if (l == k || l == j || l == i)
							continue;
						Combination houseFour = possibleHouses.get(l);
						if (houseFour.isDuplicate(houseThree) || houseFour.isDuplicate(houseTwo)
								|| houseFour.isDuplicate(houseOne))
							continue;

						for (int m = 0; m < possibleHouses.size(); m++) {
							if (m == l || m == k || m == j || m == i)
								continue;
							Combination houseFive = possibleHouses.get(m);
							if (houseFive.isDuplicate(houseFour) || houseFive.isDuplicate(houseThree)
									|| houseFive.isDuplicate(houseTwo) || houseFive.isDuplicate(houseOne))
								continue;

							List<Combination> currentSolution = new ArrayList<>();
							currentSolution.add(houseOne);
							currentSolution.add(houseTwo);
							currentSolution.add(houseThree);
							currentSolution.add(houseFour);
							currentSolution.add(houseFive);
							solutions.add(currentSolution);
						}
					}
				}
			}
		}

		// Remove the solutions that don't respect the house relative position.
		System.out.println("Checking list rules");
		solutions = solutions.parallelStream().filter(greenHouseRightToIvory).filter(chesterfieldsSmokerNextToFox)
				.filter(koolsSmokerNextToHorse).filter(norwegianLivesNextToBlueHouse).collect(Collectors.toList());

		// Filters duplicates and checks that we only found one solution.
		System.out.println("Filtering duplicates and checking that we only have one solution");
		solutions = solutions.parallelStream().map(HashSet::new).distinct().map(ArrayList::new)
				.collect(Collectors.toList());

		assert solutions.size() == 1;

		// Sorts the solution and prints it.
		List<Combination> solution = solutions.get(0);
		solution.sort(Comparator.comparing(Combination::getHouse));
		solution.forEach(System.out::println);
	}

}