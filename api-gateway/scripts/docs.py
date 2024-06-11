#!/bin/python3

import os
import shutil

services = ['crm', ]
env_services = f'SERVICES={",".join(services)}'
profiles = 'SPRING_PROFILES_ACTIVE=api-docs,no-security'

print('Create json documentation')
os.system(f'{env_services} {profiles} ./gradlew generateOpenApiDocs --info --rerun')
services += ['api-gateway']

print('Create ouptut folder for typescript-axios')
out_axios = 'build/typescritp-axios-full'
os.makedirs(out_axios, exist_ok=True)
for service in services:
    os.system(f'{env_services} GENERATOR_NAME=typescript-axios INPUT_SPEC=openapi-{service}.json {profiles} ./gradlew openApiGenerate --info --rerun')
    out_generator = 'build/openapi-gen/typescript-axios'

    os.makedirs(f'{out_axios}', exist_ok=True)

    files_to_move = ['base.ts', 'api.ts', 'common.ts', 'configuration.ts']
    for file in files_to_move:
        os.makedirs(f'{out_axios}/{service}', exist_ok=True)
        shutil.copyfile(f'{out_generator}/{file}', f'{out_axios}/{service}/{file}')

    shutil.rmtree(f'{out_generator}', True)
