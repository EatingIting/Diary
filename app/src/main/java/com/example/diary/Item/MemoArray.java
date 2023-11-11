package com.example.diary.Item;

import java.util.ArrayList;

public class MemoArray {
    static private MemoArray instance;
    static private ArrayList<MemoItem> memoItems;

    private MemoArray() {
    }

    // 여러 객체가 생기면 안되니 싱글턴 패턴으로 instance를 하나만 사용.
    static public MemoArray getInstance(){
        if(instance == null){
            synchronized (MemoArray.class){
                if(instance == null){
                    instance = new MemoArray();
                    memoItems = new ArrayList<>();
                }
            }
        }
        return instance;
    }

    public static void setInstance(MemoArray instance) {
        MemoArray.instance = instance;
    }

    public void addMemoItem(MemoItem memoItem){
        memoItems.add(memoItem);
    }

    public void removeMemoItem(MemoItem memoItem){
        memoItems.remove(memoItem);
    }

    public ArrayList<MemoItem> getMemoItems() {
        return memoItems;
    }

    public void setMemoItems(ArrayList<MemoItem> memoItems) {
        MemoArray.memoItems = memoItems;
    }

    public String toStringItems(){
        String result = "";
        for (MemoItem m: memoItems) {
            result = result+m.toString();
        }
        return result;
    }
}

