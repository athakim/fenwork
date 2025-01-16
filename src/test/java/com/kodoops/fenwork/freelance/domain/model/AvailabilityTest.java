package com.kodoops.fenwork.freelance.domain.model;


import com.kodoops.fenwork.freelance.domain.vo.Availability;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityTest {

    @Test
    void shouldHaveCorrectEnumValues() {
        assertEquals(3, Availability.values().length);
        assertEquals(Availability.AVAILABLE, Availability.valueOf("AVAILABLE"));
        assertEquals(Availability.UNAVAILABLE, Availability.valueOf("UNAVAILABLE"));
        assertEquals(Availability.BUSY, Availability.valueOf("BUSY"));
    }
}
