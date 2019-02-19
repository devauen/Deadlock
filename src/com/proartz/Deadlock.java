package com.proartz;

public class Deadlock {
    static class Friend{
        private final String name;
        public Friend(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public synchronized void bow(Friend bower){
            System.out.format("%s: %s bow to me%n", this.getName(), bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower){
            System.out.format("%s: %s has bowedback to me%n", this.getName(), bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alfonso = new Friend("Alfonso");
        final Friend gatsby = new Friend("Gatsby");

        new Thread(new Runnable() {
            public void run() { alfonso.bow(gatsby); }
        }).start();

        new Thread(new Runnable() {
            public void run() { gatsby.bow(alfonso); }
        }).start();
    }
}
