package io.swagger.controller;

import io.swagger.model.VehiclesPositions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface PositionsController {

    ResponseEntity<?> addPositions(HttpServletRequest request, VehiclesPositions body);

}
