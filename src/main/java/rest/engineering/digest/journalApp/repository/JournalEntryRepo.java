package rest.engineering.digest.journalApp.repository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rest.engineering.digest.journalApp.entity.JournalEntry;
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
}
