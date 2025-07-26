package codr7.shi.libraries.core;

import codr7.shi.CellType;
import codr7.shi.ICellType;

public final class TMeta extends CellType<ICellType> {
    public TMeta(final String name, final ICellType... parents) {
        super(name, parents);
    }
}
