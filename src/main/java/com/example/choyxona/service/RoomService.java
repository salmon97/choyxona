package com.example.choyxona.service;

import com.example.choyxona.entity.Client;
import com.example.choyxona.entity.Day;
import com.example.choyxona.entity.Room;
import com.example.choyxona.entity.enums.Status;
import com.example.choyxona.payload.ApiResponse;
import com.example.choyxona.payload.ResRoom;
import com.example.choyxona.repository.ClientRepository;
import com.example.choyxona.repository.DayRepository;
import com.example.choyxona.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DayRepository dayRepository;

    public ApiResponse getTodayRoom() {
        LocalDate t = LocalDate.now();
        int yearNum = t.getYear();
        int monthNum = t.getMonth().getValue();
        int dayNum = t.getDayOfMonth();
        Day day = dayRepository.findByDayNumAndMonth_MonthNumAndMonth_Year(dayNum, monthNum, yearNum);
        return new ApiResponse("ok", day != null, day != null ? roomsChecked(day.getId()) : null);
    }

    public List<ResRoom> roomsChecked(long dayId) {
        List<Room> all = roomRepository.findAll();
        List<ResRoom> resRooms = new ArrayList<>();
        Day day = dayRepository.getOne(dayId);
        List<Client> allByDay_id = clientRepository.findAllByDay_Id(dayId);
        boolean a;

        String m = day.getMonth().getMonthNum() < 10 ? "-0" + day.getMonth().getMonthNum() : "-" + day.getMonth().getMonthNum().toString();
        String d = day.getDayNum() < 10 ? "-0" + day.getDayNum() : "-" + day.getDayNum().toString();
        for (Room room : all) {
            a = true;
            for (Client client : allByDay_id) {
                if (Objects.equals(room.getId(), client.getRoom().getId()) && client.getStatus() != Status.FINISHED) {
                    a = false;
                    resRooms.add(new ResRoom(client.getId(), room.getId(), dayId, "band", room.getName(), room.getCapacity(),
                            day.getMonth().getYear() + m + d)
                    );
                }
            }
            if (a) {
                resRooms.add(new ResRoom(null, room.getId(), dayId, "bo'sh", room.getName(), room.getCapacity(),
                        day.getMonth().getYear() + m + d
                ));
            }
        }
        return resRooms;
    }

    public void saveEdit(Room room) {
        roomRepository.save(room);
    }

    public List<Room> roomList() {
        return roomRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void delete(long id) {
        roomRepository.deleteById(id);
    }
}
