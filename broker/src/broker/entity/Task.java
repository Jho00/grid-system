package broker.entity;

import broker.entity.interfaces.Executable;

public class Task implements Executable {
    @Override
    public boolean execute() {
        return false;
    }
}
