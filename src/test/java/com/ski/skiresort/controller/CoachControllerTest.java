package com.ski.skiresort.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ski.skiresort.domain.entity.Coach;
import com.ski.skiresort.dto.CoachDto;
import com.ski.skiresort.dto.CoachResponse;
import com.ski.skiresort.service.CoachService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = CoachController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class CoachControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoachService coachService;

    @Autowired
    private ObjectMapper objectMapper;
    private Coach coach;
    private CoachDto coachDto;

    @BeforeEach
    public void init(){
        coach = Coach.builder().fullName("Misha").category("ski").dateOfBirth("01.01.2000").gender("Male").photo("qwertyu").build();
        coachDto = CoachDto.builder().fullName("Misha").category("ski").dateOfBirth("01.01.2000").gender("Male").photo("qwertyu").build();

    }
    @Test
    public void CoachController_AddCoach_ReturnCreated() throws Exception{
        given(coachService.createCoach(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/coaches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(coachDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName", CoreMatchers.is(coachDto.getFullName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", CoreMatchers.is(coachDto.getCategory())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", CoreMatchers.is(coachDto.getDateOfBirth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender", CoreMatchers.is(coachDto.getGender())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photo", CoreMatchers.is(coachDto.getPhoto())));
    }
    @Test
    public void CoachController_GetAllCoach_ReturnResponseDto() throws Exception {
        CoachResponse responseDto = CoachResponse.builder().pageSize(10).last(true).pageNo(1).content(Arrays.asList(coachDto)).build();
        when(coachService.getAllCoach(1,10)).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/api/coach")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo","1")
                .param("pageSize", "10"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(responseDto.getContent().size())));
    }
    @Test
    public void CoachController_CoachDetail_ReturnCoachDto() throws Exception {
        Long coachId = 1L;
        when(coachService.getCoachById(coachId)).thenReturn(coachDto);

        ResultActions response = mockMvc.perform(get("/api/coaches/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(coachDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName", CoreMatchers.is(coachDto.getFullName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", CoreMatchers.is(coachDto.getCategory())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", CoreMatchers.is(coachDto.getDateOfBirth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender", CoreMatchers.is(coachDto.getGender())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photo", CoreMatchers.is(coachDto.getPhoto())));
    }
    @Test
    public void CoachController_UpdateCoach_ReturnCoachDto() throws Exception {
        Long coachId = 1L;
        when(coachService.updateCoach(coachDto, coachId)).thenReturn(coachDto);

        ResultActions response = mockMvc.perform(put("/api/coaches/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(coachDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName", CoreMatchers.is(coachDto.getFullName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", CoreMatchers.is(coachDto.getCategory())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", CoreMatchers.is(coachDto.getDateOfBirth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender", CoreMatchers.is(coachDto.getGender())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photo", CoreMatchers.is(coachDto.getPhoto())));
    }
    @Test
    public void CoachController_DeleteCoach_ReturnString() throws Exception {
        Long coachId = 1L;
        doNothing().when(coachService).deleteCoachById(1L);

        ResultActions response = mockMvc.perform(delete("/api/coaches/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}











