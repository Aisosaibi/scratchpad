from database import notes_collection
from bson import ObjectId

async def insert_note(note_dict: dict) -> dict:
    result = await notes_collection.insert_one(note_dict)
    note_dict["_id"] = result.inserted_id
    return note_dict

async def find_note_by_id(note_id: str) -> dict | None:
    return await notes_collection.find_one({"_id": ObjectId(note_id)})

async def find_all_notes() -> list[dict]:
    return await notes_collection.find().to_list(length=None)

async def update_note(note_id: str, updates: dict) -> dict | None:
    await notes_collection.update_one({"_id": ObjectId(note_id)}, {"$set": updates})
    return await find_note_by_id(note_id)

async def delete_note(note_id: str) -> None:
    await notes_collection.delete_one({"_id": ObjectId(note_id)})