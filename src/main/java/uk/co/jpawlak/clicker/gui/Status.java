package uk.co.jpawlak.clicker.gui;




public enum Status
{
  ACTIVE(0),
  INACTIVE(0),
  COUNTDOWN(3);

  private int time;

  private Status(int time) {
    this.time = time;
  }

  public static Status countdown(int time) {
    COUNTDOWN.time = time;
    return COUNTDOWN;
  }

  public int getTime() {
    return this.time;
  }

  public Status decrement() {
    this.time -= 1;
    return this;
  }
}


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\gui\Status.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */