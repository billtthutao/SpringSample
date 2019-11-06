package cadt.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class EmailRequest {
	private final String[] receiver;
	private final String[] cc;
	private final String subject;
	private final String body;
}
