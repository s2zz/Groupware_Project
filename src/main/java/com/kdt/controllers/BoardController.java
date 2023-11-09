package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.MembersDTO;
import com.kdt.dto.Mk_BoardDTO;
import com.kdt.services.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	BoardService bservice;
	
	@RequestMapping("sideBar")
	public String sideBar(Model model) {
		List<Mk_BoardDTO> group_list = bservice.select_board_type_group();
		List<Mk_BoardDTO> all_list = bservice.select_board_type_all();
		model.addAttribute("group_list",group_list);
		model.addAttribute("all_list",all_list);
		return "boards/sideBar";
	}
	
	@RequestMapping("toFavoriteBoard")
	public String toFavoriteBoard() {
		return "boards/favorite_board";
	}
	
	@RequestMapping("toMk_board")
	public String toMk_board(Model model){
		List<String> organizationList = bservice.selectAllOrganization(); // member service로 바꿀 예정		
		model.addAttribute("organizationList",organizationList);
		return "boards/mk_board";
	}
	
	// MemberController로 옮겨라
	@ResponseBody
	@RequestMapping("selectByOrganization")
	public List<String> selectByOrganization(String organization){
		return bservice.selectByOrganization(organization);
	}
	
	@ResponseBody
	@RequestMapping("selectByJobName")
	public List<String> selectByJobName(String job_name){
		return bservice.selectByJobName(job_name);
		
	}
	
	@ResponseBody
	@RequestMapping("selectMemberByOrganization")
	public List<MembersDTO> selectMemberByOrganization(String organization){
		return bservice.selectMemberByOrganization(organization);
	}
	
	@ResponseBody
	@RequestMapping("selectMemberByOrganizationAndJobName")
	public List<MembersDTO> selectMemberByOrganizationAndJobName(String organization, String job_name){
		return bservice.selectMemberByOrganizationAndJobName(organization,job_name);
	}
	
	@ResponseBody
	@RequestMapping("selectMemberByName")
	public MembersDTO selectMemberByName(MembersDTO dto){
		return bservice.selectMemberByName(dto);
	}
	
	
	////////////////
	
	@RequestMapping("toEditBoard")
	public String toEditBoard() {
		return "boards/edit_board";
	}
	
	@RequestMapping("toContentsBoard")
	public String toContentsBoard() {
		return "boards/contents_board";
	}
	@RequestMapping("toWriteContentsBoard")
	public String toWriteContentsBoard() {
		return "boards/write_contents_board";
	}
}
