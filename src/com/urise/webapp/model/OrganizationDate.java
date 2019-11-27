package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.Objects;

public class OrganizationDate {

    private YearMonth dateStart;
    private YearMonth dateEnd;

    public OrganizationDate(int startYear, int startMonth, int endYear, int endMonth) {
        this.dateStart = YearMonth.of(startYear, startMonth);
        this.dateEnd = YearMonth.of(endYear, endMonth);
    }

    public YearMonth getDateStart() {
        return dateStart;
    }

    public YearMonth getDateEnd() {
        return dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationDate date = (OrganizationDate) o;
        return dateStart.equals(date.dateStart) &&
                dateEnd.equals(date.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateEnd);
    }

    @Override
    public String toString() {
        return "OrganizationDate{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
