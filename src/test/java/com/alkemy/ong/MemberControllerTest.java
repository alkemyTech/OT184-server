package com.alkemy.ong;


import com.alkemy.ong.data.repositories.MembersRepository;
import com.alkemy.ong.web.controllers.MemberController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static com.alkemy.ong.web.controllers.MemberController.*;


import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class MemberControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private MembersRepository membersRepository;

    @Autowired
    private ObjectMapper objectMapper;


   /* @Test
    @WithMockUser(authorities = "ADMIN")
    void when_callSaveMethod_then_returnsMemberEntity() throws Exception {
        var memberEntity = createEntity(null, "Francisco", "FranciscoF", "FranciscoI", "FranciscoLinkedin", "FranciscoImage", null);
        var memberEntityResponse = createEntity(1L, "FranciscoName", "FranciscoFace", "FranciscoInsta", "FranciscoLinkedin", "FranciscoImage", "FranciscoDescription");

        when(membersRepository.save(memberEntity)).thenReturn(memberEntityResponse);

        mvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberEntityResponse)))
                .andExpect(jsonPath("$", aMapWithSize(7)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("FranciscoName")))
                .andExpect(jsonPath("$.facebookUrl", is("FranciscoFace")))
                .andExpect(jsonPath("$.instagramUrl", is("FranciscoInsta")))
                .andExpect(jsonPath("$.linkedinUrl", is("FranciscoLinkedin")))
                .andExpect(jsonPath("$.image", is("FranciscoImage")))
                .andExpect(jsonPath("$.description", is("FranciscoDescription")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(membersRepository, times(1)).save(memberEntity);

    }
*/
    @Test
    void findByPage() {
    }
/*

    @Test
    @WithMockUser(authorities = "ADMIN")
    void when_callDeleteMethod_then_returnsIsNoContent() throws Exception {
        var memberEntity = createEntity(1L, "FranciscoName", "FranciscoFace", "FranciscoInsta", "FranciscoLinkedin", "FranciscoImage", "FranciscoDescription");

        when(membersRepository.findById(memberEntity.getId())).thenReturn(Optional.of(memberEntity));
        when(membersRepository.save(memberEntity)).thenReturn(memberEntity);

        mvc.perform(delete("/members/1")).andExpect(status().isNoContent());

    }
*/

    @Test
    @WithMockUser(authorities = "ADMIN")
    void when_callDeleteMethodUnregisteredId_then_returnsNotFound() throws Exception {
        when(membersRepository.findById(2L)).thenReturn(Optional.empty());
        mvc.perform(delete("/members/2")).andExpect(status().isNotFound());
    }
/*

    @Test
    @WithMockUser(authorities = "ADMIN")
    void when_updateMember_then_returnIsOk() throws Exception {
        var memberEntity = createEntity(1L, "FranciscoName", "FranciscoFace", "FranciscoInsta", "FranciscoLinkedin", "FranciscoImage", "FranciscoDescription");

        when(membersRepository.findById(memberEntity.getId())).thenReturn(Optional.of(memberEntity));
        when(membersRepository.save(memberEntity)).thenReturn(memberEntity);

        mvc.perform(put("/members/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberEntity)))
                .andExpect(jsonPath("$", aMapWithSize(7)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("FranciscoName")))
                .andExpect(jsonPath("$.facebookUrl", is("FranciscoFace")))
                .andExpect(jsonPath("$.instagramUrl", is("FranciscoInsta")))
                .andExpect(jsonPath("$.linkedinUrl", is("FranciscoLinkedin")))
                .andExpect(jsonPath("$.image", is("FranciscoImage")))
                .andExpect(jsonPath("$.description", is("FranciscoDescription")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(membersRepository, times(1)).save(memberEntity);
    }
*/

    @Test
    @WithMockUser(authorities = "ADMIN")
    void when_isMemberWhitMissingAttributes_then_returnBadRequest() throws Exception {
    var member = createDTO();

        mvc.perform(put("/members/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(status().isBadRequest());


    }

    /*private MemberEntity createEntity(Long id, String name, String facebookUrl, String instagramUrl, String linkedinUrl, String image, String description) {
        return ;
    }*/

    private MemberDTO createDTO(){
        return MemberDTO.builder()
                .id(1L).name("Francisco").facebookUrl("acebookUrl")
                .instagramUrl("instagramUrl").linkedinUrl("linkedinUrl")
                .image("image").description("description")
                .build();
    }
}