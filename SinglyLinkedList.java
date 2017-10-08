package A1;

public class SinglyLinkedList<E> {

	/*Start of Node class*/
	private static class Node<E> {
		private E element;
		private Node<E> next;

		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}

		public E getElement() {
			return this.element;
		}

		public Node<E> getNext() {
			return this.next;
		}
		
		public void setNext(Node<E> n) {
			this.next = n;
		}

	}
	/*----------endt of Node class----------*/
	
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	public SinglyLinkedList(){	
	}
	
	public int getSize(){
		return this.size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public E first(){
		if(isEmpty()){
			return null;
		}
		return head.getElement();
	}
	
	public E last(){
		if(isEmpty()){
			return null;
		}
		return tail.getElement();
	}
	
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if(size==0){
			tail = head;
		}
		size++;
	}
	
	public void addLast(E e) {
		Node<E> node = new Node<>(e,null);
		if(size==0){
			addFirst(e);
		}
		else{
			tail.setNext(node);
			tail  = node;
			size++;
		}
		
	}
	
	public void traverse() {
		Node<E> tmp = head;
		if(head==null){
			System.out.println("Empty Linked List!");
		}
		else{
			while(tmp!=null){
				System.out.println(tmp.getElement());
				tmp = tmp.getNext();
			}
		}
	}
	
	public E removeFirst() {
		if(isEmpty()){
			return null;
		}
		
		E result = head.getElement();
		head = head.getNext();
		size--;
		if(size==0){
			tail=null;
		}
		return result;
	}
	

	public static void main(String[] args) {
		SinglyLinkedList<Integer> sLinkedList  = new SinglyLinkedList<>();
		sLinkedList.addFirst(23);
		sLinkedList.addLast(34);
		sLinkedList.addFirst(43);
		sLinkedList.addFirst(3);
		sLinkedList.addFirst(83);
		sLinkedList.addFirst(63);
		sLinkedList.addFirst(41);
		sLinkedList.addFirst(43);
		//System.out.println(sLinkedList.last());
		sLinkedList.traverse();
	}
	
	
	
	
}
