package taste.r2dbcoracle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

  private final String firstName;
  private final String lastName;
  @Id
  private Long id;

}
