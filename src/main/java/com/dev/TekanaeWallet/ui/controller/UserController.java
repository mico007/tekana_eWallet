package com.dev.TekanaeWallet.ui.controller;


import com.dev.TekanaeWallet.exeptions.TekanaEWalletException;
import com.dev.TekanaeWallet.io.entity.UserEntity;
import com.dev.TekanaeWallet.service.UserService;
import com.dev.TekanaeWallet.shared.dto.UserDto;
import com.dev.TekanaeWallet.ui.model.request.UserLoginRequestModel;
import com.dev.TekanaeWallet.ui.model.request.UserRequestModel;
import com.dev.TekanaeWallet.ui.model.response.UserRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "SwaggerApiDocumentation", description = "Tekana eWallet Swagger APIs Documentation !!!!")
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "createUser", response = Iterable.class, tags = "User sign up")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created|OK"),
            @ApiResponse(code = 400, message = "bad request!!!"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})

    @PostMapping(
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    public ResponseEntity<UserEntity> createUser(@RequestBody UserRequestModel userDetails) {

        //checking if any of the required fields is empty
        if (userDetails.getFullName().isBlank() || userDetails.getEmail().isBlank() || userDetails.getPassword().isBlank())
            throw new TekanaEWalletException("Invalid data passed, please check your data.", 400);

        //transforming userDetails object to userDto object
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserEntity newUser = userService.createUser(userDto);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "loginUser", response = Iterable.class, tags = "User login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "bad request!!!"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 406, message = "not acceptable!!!")})
    @PostMapping(
            path = "/login-user",
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    public ResponseEntity<UserRes> loginUser(@RequestBody UserLoginRequestModel userDetails) {

        //checking if any of the required fields is empty
        if (userDetails.getEmail().isEmpty() || userDetails.getPassword().isEmpty())
            throw new TekanaEWalletException("Invalid data passed, please check your data.", 400);

        //transforming userDetails object to userDto object
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto loggedInUser = userService.loginUser(userDto);

        //transforming userDto object to userResponse object
        UserRes returnValue = new UserRes();
        BeanUtils.copyProperties(loggedInUser, returnValue);

        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

}
