from datetime import datetime, timezone
from pydantic import BaseModel, Field, ConfigDict


class Note(BaseModel):
    model_config = ConfigDict(populate_by_name=True)

    id: str | None = Field(default=None, alias="_id")
    title: str
    body: str
    created_at: datetime = Field(default_factory=lambda: datetime.now(timezone.utc))

    # class Config:
    #     populate_by_name = True
    #     json_encoders = {datetime: lambda dt: dt.isoformat()}


class Thing(BaseModel):
    id: str | None = Field(default=None, alias="_id")

t = Thing(**{"_id": "abc123"})
print(t.id)        # abc123 — created it using the Mongo-style name

n = Note(title="Test", body="Hello")
print(n.model_dump_json())