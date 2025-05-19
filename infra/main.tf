terraform {
  required_providers {
    mongodbatlas = {
      source  = "mongodb/mongodbatlas"
      version = "~> 1.7"
    }
  }
}

provider "mongodbatlas" {
  public_key  = var.mongodb_public_key
  private_key = var.mongodb_private_key
}

resource "mongodbatlas_project" "project" {
  name   = "franquicia2-project"
  org_id = var.org_id
}

resource "mongodbatlas_cluster" "cluster" {
  project_id                    = mongodbatlas_project.project.id
  name                          = "franquicia-cluster"
  cluster_type                  = "REPLICASET"
  provider_name                 = "TENANT"
  backing_provider_name         = "AWS"
  provider_instance_size_name   = "M0"
  provider_region_name          = "US_EAST_1"
}


resource "mongodbatlas_database_user" "user" {
  username           = "franq_user"
  password           = var.mongodb_user_password
  project_id         = mongodbatlas_project.project.id
  auth_database_name = "admin"

  roles {
    role_name     = "readWriteAnyDatabase"
    database_name = "admin"
  }
}

resource "mongodbatlas_project_ip_access_list" "access" {
  project_id = mongodbatlas_project.project.id
  cidr_block = "0.0.0.0/0"
  comment    = "Dev access"
}
