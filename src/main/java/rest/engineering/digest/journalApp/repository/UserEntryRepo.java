package rest.engineering.digest.journalApp.repository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import rest.engineering.digest.journalApp.entity.UserEntry;

public interface UserEntryRepo extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByusername(String username);

}
