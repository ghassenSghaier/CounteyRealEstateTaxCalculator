package gov.tn.taxecommune.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.entity.User;
import gov.tn.taxecommune.web.dto.UserUpdateDto;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keno&Kemo on 03.12.2017..
 */

@Service
public class UserUpdateDtoService {

    private UserService userService;
    private ModelMapper modelMapper;

    public UserUpdateDtoService(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<UserUpdateDto> findAll(){
        List<User> userList = userService.findAllEagerly();
        List<UserUpdateDto> userUpdateDtosList = new ArrayList<>();

        for(User user : userList){
            userUpdateDtosList.add(modelMapper.map(user, UserUpdateDto.class));
        }
        return userUpdateDtosList;
    }

    public UserUpdateDto findById(Long id){
        return modelMapper.map(userService.findByIdEagerly(id), UserUpdateDto.class);
    }


}
