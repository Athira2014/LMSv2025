package com.athira.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athira.demo.common.APIResponse;
import com.athira.demo.entity.Members;
import com.athira.demo.service.IMemberService;

@RestController
@RequestMapping("api/")
public class MemberController {

	@Autowired
	IMemberService memberService;

	@GetMapping("members")
	public List<Members> getAllMembers() {
		return memberService.getAllMembers();
	}

	@GetMapping("members/{id}")
	public ResponseEntity<APIResponse> getMemberById(@PathVariable Integer id) {

		APIResponse apiResponse = new APIResponse();

		try {
			Members member = memberService.getMemberById(id);

			apiResponse.setStatus(200);
			apiResponse.setError("Member found!");
			apiResponse.setData(member);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

		} catch (RuntimeException e) {

			apiResponse.setStatus(404);
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

	}

	@GetMapping("members/search/{name}")
	public ResponseEntity<APIResponse> getMemberByName(@PathVariable String name) {

		APIResponse apiResponse = new APIResponse();

		if (memberService.getMemberByName(name) == null) {
			apiResponse.setStatus(404);
			apiResponse.setError("Name error");
			apiResponse.setData("Name should contain only alphabets.");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setStatus(200);
		apiResponse.setError("Member found!");
		apiResponse.setData("Members found with name :" + name);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	@GetMapping("members/phone/{phone}")
	public List<Members> getMemberByPhone(@PathVariable String phone) {
		return memberService.getMemberByPhone(phone);
	}

	@PostMapping("members")
	public ResponseEntity<APIResponse> addMember(@RequestBody Members members) {
		
		APIResponse apiResponse = new APIResponse();
		try {
			Members member = memberService.saveMember(members);
			apiResponse.setData(member);
			apiResponse.setStatus(200);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

		}

	}

	@PutMapping("members")
	public ResponseEntity<APIResponse> editMember(@RequestBody Members members) {
		APIResponse apiResponse = new APIResponse();
		try {
			Members member = memberService.saveMember(members);
			apiResponse.setData(member);
			apiResponse.setStatus(200);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

		}

	}
	
	// Disable member by id
	@DeleteMapping("members/disable/{id}")
	private void disableMemberById(@PathVariable Integer id) {
		memberService.disableMemberById(id);
	}
}
