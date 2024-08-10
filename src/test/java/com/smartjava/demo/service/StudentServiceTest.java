package com.smartjava.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.smartjava.demo.model.StudentModel;
import com.smartjava.demo.repository.StudentRepository;
import com.smartjava.demo.response.ResultResponse;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
	
	@InjectMocks
	private StudentService studentService;
	
	@Mock
	private StudentRepository studentRepository;
	
	List<StudentModel> mockEntities;
	
	private StudentModel sModel;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	 @BeforeEach
	    public void setUp() {
	        // Initialize mockEntities with some test data
	        mockEntities = Collections.singletonList(new StudentModel()); // Add appropriate test data here
	    }


	@Test
	public void testGetListSuccess() {
		
		when(studentRepository.findAll()).thenReturn(mockEntities);
		
		ResultResponse response=studentService.getList();
		
		assertEquals("200K", response.getMetadata().getCode());
		assertEquals("Get all records from database", response.getMetadata().getMessage());
		assertEquals(mockEntities.size(), response.getMetadata().getNoOfRecords());
		assertEquals(mockEntities, response.getResult());
	
		verify(studentRepository, times(2)).findAll();

	}
	
	
	@Test
	public void testGetListFailed() {
		when(studentRepository.findAll()).thenThrow(new RuntimeException("Database error"));
		
       ResultResponse response=studentService.getList();
		
		assertEquals("400K", response.getMetadata().getCode());
		assertEquals("Failed to fetch reords", response.getMetadata().getMessage());
		assertEquals(0, response.getMetadata().getNoOfRecords());
		assertEquals(null, response.getResult());
	
		verify(studentRepository, times(1)).findAll();

	}
	
	@Test 
	public void testSaveSucess() {
		StudentModel studentModel=new StudentModel();
		studentModel.setAge(22);
		studentModel.setEmail("abc@mail.com");
		studentModel.setStudName("abc");
		
		when(studentRepository.save(sModel)).thenReturn(studentModel);
		
		ResultResponse response=studentService.save(sModel);
		
		assertEquals("200K", response.getMetadata().getCode());
		assertEquals("save Data sucessfully in database", response.getMetadata().getMessage());
		assertEquals(studentModel.getId(),response.getMetadata().getNoOfRecords());
		assertEquals(studentModel, response.getResult());
		
		verify(studentRepository, times(1)).save(sModel);
		
		
	}
	
	  @Test
	    public void testSaveFailed() {
	        // Arrange: Set up the mock to throw an exception
	        when(studentRepository.save(sModel)).thenThrow(new RuntimeException("Database error"));

	        // Act: Call the service method
	        ResultResponse response = studentService.save(sModel);

	        // Assert: Verify the response
	        assertEquals("400K", response.getMetadata().getCode());
	        assertEquals("Failed to save records in database", response.getMetadata().getMessage());
	        assertEquals(0, response.getMetadata().getNoOfRecords());
	        assertEquals(null, response.getResult());

	        // Verify: Ensure the repository's save method was called once
	        verify(studentRepository, times(1)).save(sModel);
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
