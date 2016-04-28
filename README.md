Hybris

Hybris Practice

### Folder structure
* src: all the customized extensions will be located here
* \*Pkg: all packages that some customized extensions may depend on, e.g. cuppyPkg includes all the resources our customized cuppy extensions will need

### Hybris Environment
Will skip the environment setup here, please refer to https://wiki.hybris.com or https://help.hybris.com (since version6.0) for more details

### Done
- [X] cuppytrail extension
   - Before creating this extension, please extra the cuppy.zip from cuppyPkg and put it under 'bin/custom' folder, if you put it under 'src' folder, please update the 'config/localextension.xml'
   - Create the DAO, Service, Facade layer for "Stadium" 
- [ ] cuppytrailfrontend extension
   - The storefront for cuppytrail
