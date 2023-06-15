package com.example.practica3;

import com.example.practica3.entities.Reserve;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Utils {

    public static Reserve lastReserve(List<Reserve> reserves) {

        if (reserves.isEmpty()) {
            return null; // Si la lista está vacía, retorna null
        }

        Reserve lastReserve = reserves.get(0);
        for (Reserve reserve : reserves) {
            if (reserve.getReserved_at().after(lastReserve.getReserved_at())) {
                lastReserve = reserve;
            }
        }
        return lastReserve;
    }

    public static int countReservesLastMonth(List<Reserve> reserves) {

        LocalDate today = LocalDate.now();
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        int count = 0;

        for (Reserve reserve : reserves) {
            Date reserveDate = reserve.getReserved_at();
            LocalDate localReserveDate = reserveDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (localReserveDate.isEqual(today) || (localReserveDate.isAfter(oneMonthAgo) && localReserveDate.isBefore(today.plusDays(1)))) {
                count++;
            }
        }

        return count;
    }

}
