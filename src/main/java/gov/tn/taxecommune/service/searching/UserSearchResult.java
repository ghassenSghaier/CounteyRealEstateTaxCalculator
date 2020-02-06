package gov.tn.taxecommune.service.searching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import gov.tn.taxecommune.web.dto.UserDto;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class UserSearchResult {
    private Page<UserDto> userPage;
    private boolean numberFormatException;
	public void setUserPage(Page<UserDto> findAllPageable) {
		this.userPage=findAllPageable;
		
	}
	public Page<UserDto> getUserPage() {
		return userPage;
		
	}
	public boolean isNumberFormatException() {
		return numberFormatException;
		
	}
	public UserSearchResult(Page<UserDto> userPage, boolean numberFormatException) {
		super();
		this.userPage = userPage;
		this.numberFormatException = numberFormatException;
	}
	public UserSearchResult() {
		super();
	}
	
	
}
