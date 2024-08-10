package com.smartjava.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.swing.plaf.basic.BasicGraphicsUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.smartjava.demo.model.StudentModel;
import com.smartjava.demo.repository.StudentRepository;
import com.smartjava.demo.response.MetaDataResponse;
import com.smartjava.demo.response.ResultResponse;


@Service
public class StudentService {

	@Autowired
	private StudentRepository sRepository;
	


	
	public ResultResponse getList() {
		MetaDataResponse metadataResponse = new MetaDataResponse();
		ResultResponse resultResponse = new ResultResponse();
		try {
			metadataResponse.setCode("200K");
			metadataResponse.setMessage("Get all records from database");
			metadataResponse.setNoOfRecords(sRepository.findAll().size());
			resultResponse.setMetadata(metadataResponse);
			resultResponse.setResult(sRepository.findAll());
			return resultResponse;
		} catch (Exception e) {
			e.printStackTrace();
			metadataResponse.setCode("400K");
			metadataResponse.setMessage("Failed to fetch reords");
			metadataResponse.setNoOfRecords(0);
			resultResponse.setMetadata(metadataResponse);
			resultResponse.setResult(null);
			return resultResponse;

		}
	}

	public ResultResponse save(StudentModel suModel) {
		MetaDataResponse metadataResponse = new MetaDataResponse();
		ResultResponse resultResponse = new ResultResponse();
		try {

			StudentModel studentSave = sRepository.save(suModel);

			metadataResponse.setCode("200K");
			metadataResponse.setMessage("save Data sucessfully in database");
			metadataResponse.setNoOfRecords(studentSave.getId());
			resultResponse.setMetadata(metadataResponse);
			resultResponse.setResult(studentSave);
			return resultResponse;
		} catch (Exception e) {
			e.printStackTrace();
			metadataResponse.setCode("400K");
			metadataResponse.setMessage("Failed to save records in database");
			metadataResponse.setNoOfRecords(0);
			resultResponse.setMetadata(metadataResponse);
			resultResponse.setResult(null);
			return resultResponse;

		}
	}

	public ResultResponse byId(int id) {
		MetaDataResponse metadataResponse = new MetaDataResponse();
		ResultResponse resultResponse = new ResultResponse();
		try {

			metadataResponse.setCode("200K");
			metadataResponse.setMessage("get data from data base by id");
			metadataResponse.setNoOfRecords(1);
			resultResponse.setMetadata(metadataResponse);
			resultResponse.setResult(sRepository.findById(id));
			return resultResponse;
		} catch (Exception e) {
			e.printStackTrace();
			metadataResponse.setCode("400K");
			metadataResponse.setMessage("fail to fetch records from database");
			metadataResponse.setNoOfRecords(0);
			resultResponse.setMetadata(metadataResponse);
			resultResponse.setResult(null);
			return resultResponse;

		}
	}

	public ResultResponse update(StudentModel suModel, int id) {
		MetaDataResponse metadataResponse = new MetaDataResponse();
		ResultResponse resultResponse = new ResultResponse();
		try {
			Optional<StudentModel> studeUpdate = sRepository.findById(id);
			StudentModel updateStud = studeUpdate.get();

			updateStud.setStudName(suModel.getStudName());
			updateStud.setAge(suModel.getAge());
			updateStud.setEmail(suModel.getEmail());
			StudentModel studentSave = sRepository.save(updateStud);

			metadataResponse.setCode("200K");
			metadataResponse.setMessage("student update sucessfully");
			metadataResponse.setNoOfRecords(1);
			resultResponse.setMetadata(metadataResponse);
			resultResponse.setResult(studentSave);
			return resultResponse;
		} catch (Exception e) {
			e.printStackTrace();
			metadataResponse.setCode("400K");
			metadataResponse.setMessage("failed to updated records in database");
			metadataResponse.setNoOfRecords(0);
			resultResponse.setMetadata(metadataResponse);
			resultResponse.setResult(null);
			return resultResponse;

		}

	}

	public ResultResponse delete(int id) {
		MetaDataResponse metadataResponse = new MetaDataResponse();
		ResultResponse resultResponse = new ResultResponse();
		try {

			sRepository.deleteById(id);

			metadataResponse.setCode("200K");
			metadataResponse.setMessage("delete records sucessfully");
			metadataResponse.setNoOfRecords(1);
			resultResponse.setMetadata(metadataResponse);
			resultResponse.setResult(null);
			return resultResponse;
		} catch (Exception e) {
			e.printStackTrace();
			metadataResponse.setCode("400K");
			metadataResponse.setMessage("fail to delete records from database");
			metadataResponse.setNoOfRecords(0);
			resultResponse.setMetadata(metadataResponse);
			resultResponse.setResult(null);
			return resultResponse;

		}

	}

	
}
