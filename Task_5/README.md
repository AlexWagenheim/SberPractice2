# RestMusic
##### _REST API_

## Описание
Отвечает на запросы GET, POST о музыкальных группах, их альбомах и треках

### Доступные пути запросов:
POST:
   - `/group` - создать новую музыкальную группу
   - `/group/{groupId}/album` - создать новый альбом у группы с __id__ = `groupId`
   - `/group/{groupId}/album/{albumId}/track` - создать новый трек в альбоме с __id__ = `albumId` у группы с __id__ = `groupId`

GET:
   - `/group` - получить список всех музыкальных групп
   - `/group/{groupId}` - получить информацию о группе с __id__ = `groupId`
   - `/group/{groupId}/album` - получить список всех альбомов у группы с __id__ = `groupId`
   - `/group/{groupId}/album/{albumId}` - получить информацию об альбоме с __id__ = `albumId` у группы с __id__ = `groupId`
   - `/group/{groupId}/album/{albumId}/track` - получить список всех треков в альбоме с __id__ = `albumId` у группы с __id__ = `groupId`
   - `/group/{groupId}/album/{albumId}/track/{trackId}` - получить информацию о треке с __id__ = `trackId` в альбоме с __id__ = `albumId` у группы с __id__ = `groupId`

***
A. Wagenheim
