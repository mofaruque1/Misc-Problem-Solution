package A1;

public class DoublyLinkedList<E> {
	
	/*Start of Node class*/
	private static class Node<E> {
		private E element;
		private Node<E> next;
		private Node<E> previous;

		public Node(E element, Node<E> next, Node<E> privious) {
			this.element = element;
			this.next = next;
			this.previous = privious;
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
		
		public Node<E> getPrevious() {
			return this.previous;
		}
		
		public void setPrevious(Node<E> n) {
			this.previous= n;
		}

	}
	/*----------end of Node class----------*/
	
	private Node<E> head = null;
	private Node<E> tail = null;
	int size = 0;
	
	public DoublyLinkedList() {
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public void addFirst(E e) {
		Node<E> temp = new Node<>(e,null, null);
		if(isEmpty()){
			head = temp;
			tail = head;
		}
		else{
			temp.next = head;
			head.previous = temp;
			head  = temp;
		}
		size++;
	}
	
	public void addLast(E e) {
		Node<E> temp = new Node<>(e,null, null);
		if(isEmpty()){
			head = temp;
			tail = head;
		}
		else{
			tail.next = temp;
			temp.previous = tail;
			tail = temp;
		}
		size++;
	}
	
	
	public void delete(E e) {
		boolean exist = false;
		if(isEmpty()){
			System.out.println("Empty List");
		}else{
			Node<E> tmp = head;
			while(tmp!=null){
				if(tmp.getElement()==e){
					Node<E> tmpOne = tmp.previous;
					Node<E> tmpTwo = tmp.next;
					tmpOne.setNext(tmpTwo);
					tmpTwo.setPrevious(tmpOne);
					tmp.setNext(null);
					tmp.setPrevious(null);
					exist = true;
				}
				tmp = tmp.getNext();
			}
			if(!exist){
				System.out.println("Input doesnot exist");
			}
		}
	}
	
	
	
	public void traverse() {
		if(isEmpty()){
			System.out.println("List is empty!");
		}
		else{
			Node<E> tmp = head;
			while(tmp!=null){
				System.out.println(tmp.getElement());
				tmp = tmp.getNext();
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addFirst(23);
		list.addFirst(3);
		list.addFirst(43);
		list.addFirst(73);
		list.addLast(561);
		
		list.traverse();
		list.delete(13);
		System.out.println("After deleting: ");
		list.traverse();
		long endTime = System.currentTimeMillis();
		long elapsed = endTime-startTime;
		System.out.println(elapsed+"ms");
		
	}

}
