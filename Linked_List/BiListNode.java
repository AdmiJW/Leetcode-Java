package Linked_List;


public class BiListNode {
  public int val;
  public BiListNode next, prev;
  public BiListNode() {} 
  public BiListNode(int val) { this.val = val; }
  public BiListNode(int val, BiListNode next, BiListNode prev) {
    this.val = val;
    this.next = next;
    this.prev = prev;
  }
}