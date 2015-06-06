package de.briemla.clockradio.dabpi.result;

import java.util.ArrayList;
import java.util.stream.Stream;

public class DABServiceList {

    private final ArrayList<DABService> serviceList;

    public DABServiceList() {
        serviceList = new ArrayList<>();
    }

    public void add(DABService dabService) {
        serviceList.add(dabService);
    }

    public Stream<DABService> stream() {
        return serviceList.stream();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((serviceList == null) ? 0 : serviceList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DABServiceList other = (DABServiceList) obj;
        if (serviceList == null) {
            if (other.serviceList != null) {
                return false;
            }
        } else if (!serviceList.equals(other.serviceList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DABServiceList [serviceList=" + serviceList + "]";
    }

}
