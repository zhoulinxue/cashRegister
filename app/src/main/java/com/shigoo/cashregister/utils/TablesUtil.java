package com.shigoo.cashregister.utils;

import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.Table;

import java.util.ArrayList;
import java.util.List;

public class TablesUtil {
    public static List<Table> fillList(List<Table> tables) {
        List<Table> tableList = new ArrayList<>();
        for (Table table : tables) {
            Billbean billbean = null;
            if (table.isAssembleTable()) {
                if ("1".equals(table.getUse_tag())) {
                    billbean = table.getMainBill();
                } else {
                    billbean = table.getOrherBill();
                }
            } else if (!table.isKx()) {
                billbean = table.getMainBill();
            }
            if (billbean != null) {
                table.setLocal_status(billbean.getStatus());
                table.setCurrentBillbean(billbean);
                if (!table.isAssembleTable()) {
                    tableList.add(table);
                } else {
                    for (Billbean bill : table.getBill()) {
                        Table newtable = getTableFromBill(table, bill);
                        if (newtable != null) {
                            tableList.add(newtable);
                        }
                    }
                }
            }
        }
        return tableList;
    }

    private static Table getTableFromBill(Table table, Billbean billbean) {
        if ("0".equals(table.getUse_tag()) && "1".equals(billbean.getBill_tag())) {
            return null;
        }
        if ("2".equals(billbean.getBill_tag())) {
            billbean.setBill_tag("1");
        }
        List<Billbean> billbeans = new ArrayList<>();
        billbeans.add(billbean);
        Table newtable = null;
        try {
            newtable = (Table) table.clone();
            newtable.setBill(billbeans);
            newtable.setCurrentBillbean(billbean);
            newtable.setLocal_status(billbean.getStatus());
            newtable.setTable_number(billbean.getTable_number());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newtable;
    }
}
