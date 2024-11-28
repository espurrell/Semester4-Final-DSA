package com.keyin.bstapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.keyin.bstapp.service.TreeService;
import com.keyin.bstapp.trees.BinaryNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TreeController {

    @Autowired
    private TreeService treeService;

    @PostMapping("/process-numbers")
    public ResponseEntity<String> processNumbers(@RequestParam String numbers) {
        System.out.println("Received numbers: " + numbers); // Log the input

        try {
            // Check if the input is empty
            if (numbers == null || numbers.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Input numbers cannot be null or empty.");
            }

            // Create binary search tree
            BinaryNode tree = treeService.createBinarySearchTree(numbers);

            // Save tree to database
            treeService.saveTree(numbers, tree);

            // Convert tree to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String treeJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);

            return ResponseEntity.ok(treeJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing numbers: JSON processing error - " + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error processing numbers: Invalid number format - " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing numbers: " + e.getMessage());
        }
    }

    @GetMapping("/previous-trees")
    public ResponseEntity<?> showPreviousTrees() {
        try {
            List<TreeEntity> previousTrees = treeService.getPreviousTrees();
            return ResponseEntity.ok(previousTrees);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving previous trees: " + e.getMessage());
        }
    }
}