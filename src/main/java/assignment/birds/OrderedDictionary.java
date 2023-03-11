package assignment.birds;

public class OrderedDictionary implements OrderedDictionaryADT {

    Node root;

    OrderedDictionary() {
        root = new Node();
    }

    /**
     * Returns the Record object with key k, or it returns null if such a record
     * is not in the dictionary.
     *
     * @param k
     * @return
     * @throws assignment/birds/DictionaryException.java
     */
    @Override
    public BirdRecord find(DataKey k) throws DictionaryException {
        Node current = root;
        int comparison;
        if (root.isEmpty()) {         
            throw new DictionaryException("There is no record matches the given key");
        }

        while (true) {
            comparison = current.getData().getDataKey().compareTo(k);
            if (comparison == 0) { // key found
                return current.getData();
            }
            if (comparison == 1) {
                if (current.getLeftChild() == null) {
                    // Key not found
                    throw new DictionaryException("There is no record matches the given key");
                }
                current = current.getLeftChild();
            } else if (comparison == -1) {
                if (current.getRightChild() == null) {
                    // Key not found
                    throw new DictionaryException("There is no record matches the given key");
                }
                current = current.getRightChild();
            }
        }

    }

    /**
     * Inserts r into the ordered dictionary. It throws a DictionaryException if
     * a record with the same key as r is already in the dictionary.
     *
     * @param r
     * @throws birds.DictionaryException
     */
    @Override
    public void insert(BirdRecord r) throws DictionaryException {
        // Write this method
        Node newEntry = new Node(r);

        if(root.getData().getDataKey() == null) {
            root = newEntry;    // newEntry is root if root is empty
        } else {
            Node parent = new Node();
            Node currentNode = root;

            while(currentNode != null) {
                parent = currentNode;
                int result = newEntry.getData().getDataKey().compareTo(currentNode.getData().getDataKey());

                if (result == 0) {
                    System.out.println("item exists");
                    return;
                } else if (result == -1) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode = currentNode.getRightChild();
                }
            }

            newEntry.setParent(parent);
            if(newEntry.getData().getDataKey().compareTo(parent.getData().getDataKey()) == -1) {
                parent.setLeftChild(newEntry);
            } else {
                parent.setRightChild(newEntry);
            }

//            System.out.println(newEntry.getData().getDataKey().getBirdName());
//            System.out.println(newEntry.getParent().getData().getDataKey().getBirdName());
//            System.out.println("---------------------");
        }
    }

    /**
     * Removes the record with Key k from the dictionary. It throws a
     * DictionaryException if the record is not in the dictionary.
     *
     * @param k
     * @throws birds.DictionaryException
     */
    @Override
    public void remove(DataKey k) throws DictionaryException {
        // Write this method
    }

    /**
     * Returns the successor of k (the record from the ordered dictionary with
     * smallest key larger than k); it returns null if the given key has no
     * successor. The given key DOES NOT need to be in the dictionary.
     *
     * @param k
     * @return
     * @throws birds.DictionaryException
     */
    @Override
    public BirdRecord successor(DataKey k) throws DictionaryException{
        Node currentNode = root;
        Node smallestValidNode = null;
        while(currentNode != null) {
            int result = k.compareTo(currentNode.getData().getDataKey());
            //Node is larger than k and no left child
            if (result == -1) {
                //No smaller node exists
                if(!currentNode.hasLeftChild()) {
                    return currentNode.getData();
                }
                else {
                    //Store this node in case there are no smaller valid results
                    smallestValidNode = currentNode;
                    currentNode = currentNode.getLeftChild();
                }
            }
            else if(result >= 0) {
                if(currentNode.hasRightChild()) {
                    currentNode = currentNode.getRightChild();
                }
                else {
                    //Node is less than, but no greater nodes exist
                    if(smallestValidNode != null) {
                        return smallestValidNode.getData();
                    }
                    else {
                        break;
                    }
                }
            }
        }
        //No nodes larger than k
        throw new DictionaryException("There is no successor for the given record key");
    }

   
    /**
     * Returns the predecessor of k (the record from the ordered dictionary with
     * largest key smaller than k; it returns null if the given key has no
     * predecessor. The given key DOES NOT need to be in the dictionary.
     *
     * @param k
     * @return
     * @throws birds.DictionaryException
     */
    @Override
    public BirdRecord predecessor(DataKey k) throws DictionaryException{
        Node currentNode = root;
        Node largestValidNode = null;
        while(currentNode != null) {
            int result = k.compareTo(currentNode.getData().getDataKey());
            //Node is smaller than k
            if (result == 1) {
                //No larger node exists
                if(!currentNode.hasRightChild()) {
                    return currentNode.getData();
                }
                else {
                    //Store this node in case there are no larger valid results
                    largestValidNode = currentNode;
                    currentNode = currentNode.getRightChild();
                }
            }
            else if(result <= 0) {
                if(currentNode.hasLeftChild()) {
                    currentNode = currentNode.getLeftChild();
                }
                else {
                    //Node is greater than, but no smaller nodes exist
                    if(largestValidNode != null) {
                        return largestValidNode.getData();
                    }
                    else {
                        break;
                    }
                }
            }
        }
        //No nodes larger than k
        throw new DictionaryException("There is no predecessor for the given record key");
    }

    /**
     * Returns the record with smallest key in the ordered dictionary. Returns
     * null if the dictionary is empty.
     *
     * @return
     */
    @Override
    public BirdRecord smallest() throws DictionaryException{
        if(root == null)
        {
            throw new DictionaryException("Dictionary is empty");
        }
        Node currentNode = root;
        while(currentNode.hasLeftChild()) {
            currentNode = currentNode.getLeftChild();
        }
        return currentNode.getData();
    }

    /*
	 * Returns the record with largest key in the ordered dictionary. Returns
	 * null if the dictionary is empty.
     */
    @Override
    public BirdRecord largest() throws DictionaryException{
        // Write this method
        return null; // change this statement
    }
      
    /* Returns true if the dictionary is empty, and true otherwise. */
    @Override
    public boolean isEmpty (){
        return root.isEmpty();
    }
}
