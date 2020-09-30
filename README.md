# Spring Cloud Vault Example

This project includes a demo on how to consume Vault KVs and Dynamic MySQL
database credentials in a Spring App using annotations.

## Demo Steps

1. Start Vault and MySQL

```sh
cd ./scripts
./start-vault.sh
./start-mysql.sh
cd ../
```

1. Configure Vault

```sh
./configure-vault.sh
```

1. Get the root token from the output file

```sh
cat /tmp/vault-output.txt | grep "Root Token"
```

Copy the root token to the `spring.cloud.vault.token` file in
`./src/main/resources/bootstrap.properties` file.

> This repo will be updated soon to use other authentication methods

1. Run the build

```sh
./gradlew bootRun
```

### Endpoints
There are a few endpoints you can use to see the KV, credentials, and database
data.

1. `/getdbcredentials`

Will output the dynamically generated database `user` and the hashed
`authentication_string`. This demonstrates that the dynamic user is generated
using the Vault configurations in `bootstrap.yml` in conjunction with the
Autowired DataSource.

1. `/getkvdata`

Will output the KV data entered during the vault configuration step above. This
demonstrates how to use Value bean annotations to map data in the Vault KV.

1. `/getdbdata`

Will output data entered into the database. This demonstrates that the
dynamically generated `user` and `authentication_string` can be used to
successfully pull data from the database and map to a custom `Model` class.

## Cleanup

1. Cleanup the Vault deployment by running `./scripts/cleanup.sh`
