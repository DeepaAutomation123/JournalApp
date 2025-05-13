package rest.engineering.digest.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journalEntries")
@Data
@NoArgsConstructor

public class JournalEntry {
    @Id
    private ObjectId id;
   @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}
