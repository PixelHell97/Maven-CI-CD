package storage

import model.Transaction

class InMemoryTransactionStorage : TransactionStorage {

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun save(transaction: Transaction) {
        transactions.add(transaction)
        //println(transactions)
    }

    override fun edit(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(transactionId: String): Boolean {
        val transactionIndex = transactions.indexOfFirst { it.id == transactionId }
        return if(transactionIndex != -1){
            transactions.removeAt(transactionIndex)
            true
        } else {
            false
        }
    }

    override fun load(): List<Transaction> {
        return transactions
    }
}