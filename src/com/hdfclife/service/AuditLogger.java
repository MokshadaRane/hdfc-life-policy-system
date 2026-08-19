package com.hdfclife.service;

import com.hdfclife.exception.PolicyServiceException;
import com.hdfclife.model.Claim;
import com.hdfclife.model.Policy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.ArrayList;

public class AuditLogger implements AutoCloseable{

    ArrayList<String> audits = new ArrayList<>();

    public void log(Policy policy){
        String s = policy.getPolicyNo() + " | " + policy.getCustomer()+ " | " + policy.getStatus()+ " | " + Instant.now().toString();
        audits.add(s);
    }

    public void log(Claim claim){
        String s = "Claim Filed: " + claim.getPolicyNo() + " | " + claim.getClaimAmount()+ " | " + claim.getStatus()+ " | " + Instant.now().toString();
        audits.add(s);
    }

    public void logToFile() {
        try{
            FileWriter fw = new FileWriter("audit.log");
            for(String str : audits){
                fw.write(str + "\n");
            }
            fw.close();
        } catch (IOException e){
            throw new PolicyServiceException("Failed to write audit log", e);
        }
    }


//    private final Path filePath;
//
//    public AuditLogger(String fileName) {
//        this.filePath = Paths.get(fileName);
//    }
//
//    public void log(String message) {
//        try(BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
//            writer.write(message);
//            writer.newLine();
//        } catch (IOException e){
//            throw new PolicyServiceException("Failed to write audit log", e);
//        }
//    }

    @Override
    public void close() throws Exception {

    }
}
