package com.example.choyxona.controller;

import com.example.choyxona.entity.Room;
import com.example.choyxona.payload.ApiResponse;
import com.example.choyxona.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/checked/{dayId}")
    public HttpEntity<?> roomsChecked(@PathVariable long dayId) {
        return ResponseEntity.ok(new ApiResponse("ok", true, roomService.roomsChecked(dayId)));
    }

    @GetMapping("/today")
    public HttpEntity<?> today() {
        return ResponseEntity.ok(roomService.getTodayRoom());
    }

    @GetMapping("/get")
    public HttpEntity<?> getMonth() {
        return ResponseEntity.ok(new ApiResponse("ok", true, roomService.roomList()));
    }

    @PostMapping("/addAndEdit")
    public HttpEntity<?> addMonth(@RequestBody Room room) {
        roomService.saveEdit(room);
        return ResponseEntity.ok("ok");
    }

//    @PutMapping("/edit")
//    public HttpEntity<?> editMonth(@RequestBody ReqMonth reqMonth) {
//        return ResponseEntity.ok(monthService.editMonth(reqMonth));
//    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable int id) {
        roomService.delete(id);
        return ResponseEntity.ok("ok");
    }

}