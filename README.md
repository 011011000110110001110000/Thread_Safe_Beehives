# Threadsafe Bees

A simple mod that fixes a critical issue with beehives, 
where the main thread modifying the entity data in the beehive's block entity
while that same data is being accessed by the IO thread
can cause the chunk the beehive is in to fail to save.
Such failure will then lead to the chunk being regenerated.

## License
MIT License
