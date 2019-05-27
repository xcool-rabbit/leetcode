//707. 设计链表
/*
  准备暑期实习面试期间做的题，数据结构忘的差不多了。
  以为只需要MyLinkedList这一种数据结构，带头结点的那种。
  看了别人的解答发现，人家另外声明了Node结点类，MyLinkedList作为了一种类似于头结点的存在，我觉得这两种处理手段都差不多，没有孰优孰劣。
  执行用时：128 ms 已经战胜 36.93 % 的 java 提交记录
*/
class MyLinkedList {
    private int val;
    private MyLinkedList next;

    /** Initialize your data structure here. */
    public MyLinkedList() {

    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        MyLinkedList iterator = this;
        while (iterator.next != null) {
            iterator = iterator.next;
            if (index == 0) {
                return iterator.val;
            } else {
                index--;
            }
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        MyLinkedList addLinkedList = new MyLinkedList();
        addLinkedList.val = val;

        addLinkedList.next = this.next;
        this.next = addLinkedList;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        MyLinkedList addLinkedList = new MyLinkedList();
        addLinkedList.val = val;

        MyLinkedList iterator = this;
        while (iterator.next != null) {
            iterator = iterator.next;
        }
        iterator.next = addLinkedList;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        MyLinkedList addLinkedList = new MyLinkedList();
        addLinkedList.val = val;

        if (index < 0) {
            index = 0;
        }

        MyLinkedList iterator = this;
        while (iterator.next != null) {
            if (index == 0) {
                break;
            }
            iterator = iterator.next;
            index--;
        }
        if (index == 0) {
            addLinkedList.next = iterator.next;
            iterator.next = addLinkedList;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        MyLinkedList iterator = this;
        while (iterator.next != null) {
            if (index == 0) {
//                iterator.next = iterator.next == null ? null : iterator.next.next;
                iterator.next = iterator.next.next;
                break;
            }
            iterator = iterator.next;
            index--;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
