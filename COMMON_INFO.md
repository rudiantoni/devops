# Common

- Content
  - [Default ports](#default-ports)

## Default ports

| Description | Specification |
|-------------|:--------------|
| MongoDB (database) | 27017 |
| Microsoft SQL Server (database) | 1433 |
| MySQL (database) | 3306 |
| Oracle Database (database) | 1521 |
| PostgreSQL (database) | 5432 |
| Redis (database) | 6379 |
| Samba (file server) | 139, 445 |
| SSH/SFTP (communication) | 22 |

## Start projects

### Vite
- https://vitejs.dev/
- https://vitejs.dev/guide/

**[template_name] - Possible templates**
- React with JavaScript: react
- React with TypeScript: react-ts

**Project generation command**

```sh
# npm 6.x
npm create vite@latest [project_folder_name] --template [template_name]

# npm 7+, extra double-dash is needed:
npm create vite@latest [project_folder_name] -- --template [template_name]

# yarn
yarn create vite [project_folder_name] --template [template_name]

# pnpm
pnpm create vite [project_folder_name] --template [template_name]
```

**Project initialization**

After running the project creation

```sh
cd [project_folder_name]
npm install
npm run dev
```

<!-- 
| Description | Specification |
|-------------|:--------------|
| col1 | col2 |
 -->


