package softuni.exam.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapterJSON  extends TypeAdapter<LocalDateTime> {
    @Override
    public void write(final JsonWriter jsonWriter, final LocalDateTime localDateTime ) throws IOException {
        jsonWriter.value(localDateTime.toString());
    }

    @Override
    public LocalDateTime read( final JsonReader jsonReader ) throws IOException {

        return LocalDateTime.from(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").parse(jsonReader.nextString())
        );
    }
}
