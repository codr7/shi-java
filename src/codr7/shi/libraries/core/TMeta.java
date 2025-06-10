package codr7.shi.libraries.core;

import codr7.shi.ICellType;
import codr7.shi.CellType;

public final class TMeta extends CellType<ICellType> {
    public TMeta(final String name, final ICellType... parents) {
        super(name, parents);
    }
}
