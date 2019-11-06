package cadt.test;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class User {
	private final int id;
	private final String firstName;
	private final String lastName;
	
	public String toString() {
		return firstName + " " + lastName;
	}
}
