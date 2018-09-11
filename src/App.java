import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cameron.zillow.Person;


public class App {
	
	public static void main(String[] args) {
		
		// Sample data
		Iterable<Person> people = Arrays.asList(
			new Person("0001", Date.from(LocalDate.of(2000, 11, 28).atStartOfDay(ZoneId.systemDefault()).toInstant()), "Sam", "Alan", 64.5, 140.0),
			new Person("0002", Date.from(LocalDate.of(1998, 1, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()), "James", "Tully", 48.0, 134.5),
			new Person("0003", Date.from(LocalDate.of(1994, 4, 21).atStartOfDay(ZoneId.systemDefault()).toInstant()), "Tracy", "Kim", 49.2, 120.9),
			new Person("0004", Date.from(LocalDate.of(2000, 8, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), "Lori", "Baldwin", 54.0, 131.0),
			new Person("0005", Date.from(LocalDate.of(1994, 4, 21).atStartOfDay(ZoneId.systemDefault()).toInstant()), "Melody", "Anderson", 51.3, 100.3),
			new Person("0006", Date.from(LocalDate.of(1988, 6, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()), "Rufus", "Schultz", 77.7, 180.1)
		);
		
		String[] sortFields = {"ssn", "dateOfBirth", "firstName", "lastName", "heightIn", "weightLb"}; 
		String[] ascendingOptions = {"true", "false"};
		
		for (String sortField : sortFields) {
			for (String ascending: ascendingOptions) {
				System.out.printf("Sorting people by %s (ascending: %s):\n", sortField, ascending);
				
				try {
					List<Person> sortedPeople = Person.sort(people, sortField, ascending);
					sortedPeople.iterator().forEachRemaining(System.out::println);
				} catch (NoSuchFieldException e) {
					System.err.println("Unknown sort by requested field.");
				} 
				
				System.out.println(); // newline for readability between runs
			}
		}
		
	}

}
