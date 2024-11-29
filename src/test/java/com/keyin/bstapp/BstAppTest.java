package com.keyin.bstapp;

import com.keyin.bstapp.BinarySearchTree.BinarySearchTree;
import com.keyin.bstapp.BinarySearchTree.BinarySearchTreeRepository;
import com.keyin.bstapp.BinarySearchTree.BinarySearchTreeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TreesApplicationTests {

    @Autowired
    BinarySearchTreeRepository repository;

    @Autowired
    BinarySearchTreeService binarySearchTreeService;

    @Test
    void contextLoads() {
    }

    @Test
    void getBinarySearchTreeByIdReturnsBinarySearchTree() {
        int id = 101;

        BinarySearchTree tree = binarySearchTreeService.getBinarySearchTreeById(id);

        assertNotNull(tree);
        assertEquals(id, tree.getId());
        assertInstanceOf(BinarySearchTree.class, tree);
    }

    @Test
    void createBinarySearchTreeReturnsBalancedBinarySearchTree() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(10);
        integerList.add(5);
        integerList.add(15);

        BinarySearchTree binaryTree = binarySearchTreeService.createBinarySearchTree(integerList);

        assertNotNull(binaryTree);
        assertEquals(10, binaryTree.getRoot().value);
        assertEquals(5, binaryTree.getRoot().getLeftChild().value);
        assertEquals(15, binaryTree.getRoot().getRightChild().value);
    }

    @Test
    void getAllTreesReturnsArrayListOfBinarySearchTrees() {
        assertInstanceOf(ArrayList.class, binarySearchTreeService.getAllTrees());

        for (BinarySearchTree binaryTree : binarySearchTreeService.getAllTrees()) {
            assertInstanceOf(BinarySearchTree.class, binaryTree);
        }
    }
}