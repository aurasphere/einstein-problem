package co.aurasphere.einstein;

public class Combination {

	House house;
	Color color;
	Nationality nationality;
	Drink drink;
	Smoke smoke;
	Pet pet;

	public Combination(House house, Color color, Nationality nationality, Drink drink, Smoke smoke, Pet pet) {
		this.house = house;
		this.color = color;
		this.nationality = nationality;
		this.drink = drink;
		this.smoke = smoke;
		this.pet = pet;
	}

	public House getHouse() {
		return house;
	}

	public Color getColor() {
		return color;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public Drink getDrink() {
		return drink;
	}

	public Smoke getSmoke() {
		return smoke;
	}

	public Pet getPet() {
		return pet;
	}

	public boolean isDuplicate(Combination other) {
		return this.house == other.house || this.color == other.color || this.nationality == other.nationality
				|| this.drink == other.drink || this.smoke == other.smoke || this.pet == other.pet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((drink == null) ? 0 : drink.hashCode());
		result = prime * result + ((house == null) ? 0 : house.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		result = prime * result + ((pet == null) ? 0 : pet.hashCode());
		result = prime * result + ((smoke == null) ? 0 : smoke.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Combination other = (Combination) obj;
		if (color != other.color)
			return false;
		if (drink != other.drink)
			return false;
		if (house != other.house)
			return false;
		if (nationality != other.nationality)
			return false;
		if (pet != other.pet)
			return false;
		if (smoke != other.smoke)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Combination [house=" + house + ", color=" + color + ", nationality=" + nationality + ", drink=" + drink
				+ ", smoke=" + smoke + ", pet=" + pet + "]";
	}

}