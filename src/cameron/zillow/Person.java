/**
 * 
 */
package cameron.zillow;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Cameron Palk
 *
 */
public class Person {
	String ssn;
	Date dateOfBirth;
	String firstName;
	String lastName;
	Double heightIn;
	Double weightLb;
	
	public Person(String ssn, Date dateOfBirth, String firstName, String lastName, Double heightIn, Double weightLb) {
		this.ssn = ssn;
		this.dateOfBirth = dateOfBirth;
		this.firstName = firstName;
		this.lastName = lastName;
		this.heightIn = heightIn;
		this.weightLb = weightLb;
	}
	
	public String toString() {
		return String.format("%s - %s %s, born %tm/%4$td/%4$tY, %.1fin %.1flb", this.ssn, this.firstName, this.lastName, this.dateOfBirth, this.heightIn, this.weightLb);
	}
	
	/**
	 * Sorts a list of people based on the supplied sort field and direction.
	 * 
	 * @param people
	 * @param sortField is a field on Person used as sort key
	 * @param ascending is a flag whether to sort ascending of descending
	 * @return List of people sorted based on params
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static List<Person> sort(Iterable<Person> people, String sortField, String ascending) throws NoSuchFieldException {
		Field fieldToSort = Person.class.getDeclaredField(sortField);
		Function<Person, Object> getSortFieldFromPerson = person -> {
			try {
				Object fieldObject = fieldToSort.get(person);
				return fieldObject;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		};
		
		Comparator<Person> peopleComparator = Comparator.comparing(getSortFieldFromPerson, Person::compareObjects);
		Boolean reverseOrder = !ascending.matches("true");
		return StreamSupport
				.stream(people.spliterator(), false)
				.sorted(reverseOrder ? peopleComparator.reversed() : peopleComparator)
				.collect(Collectors.toList());
	}
	
	/**
	 * This method compares the two objects using the appropriate comparison function 
	 * based on the instance type of the first object.
	 * @param t1
	 * @param t2
	 * @return a comparison between the objects used in sorting
	 */
	private static Integer compareObjects(Object o1, Object o2) {
		if (o1 instanceof Date) {
			return ((Date) o1).compareTo((Date) o2);
		} else if (o1 instanceof Integer) {
			return ((Integer) o1).compareTo((Integer) o2);		
		} else if (o1 instanceof Double) {
			return ((Double) o1).compareTo((Double) o2);
		} else if (o1 instanceof String) {
			return ((String) o1).compareTo((String) o2);
		} else {
			return 0;
		}
	}
	
	
}
